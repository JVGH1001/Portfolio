package com.example.demo.services;

import com.example.demo.dao.*;
import com.example.demo.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final VacationRepository vacationRepository;
    private final ExcursionRepository excursionRepository;
    private final CartItemRepository cartItemRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository,
                        VacationRepository vacationRepository, ExcursionRepository excursionRepository,
                        CartItemRepository cartItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.vacationRepository = vacationRepository;
        this.excursionRepository = excursionRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        String orderTrackingNumber = generateOrderTrackingNumber();

        // Generate tracking
        purchase.getCart().setOrderTrackingNumber(orderTrackingNumber);

        purchase.getCart().setStatus(StatusType.ordered);
        if (purchase.getCartItems().isEmpty()) { orderTrackingNumber = "*** EMPTY CART ***"; }
        else {
            // Fetch vacation
            Vacation vacation = purchase.getCartItems()
                    .stream()
                    .findFirst()
                    .map(CartItem::getVacation)
                    .orElseThrow(() -> new IllegalArgumentException("Vacation cannot be null."));

            vacationRepository.save(vacation);

            Cart savedCart = cartRepository.save(purchase.getCart());

            Optional.ofNullable(vacation.getExcursions())
                    .ifPresent(excursions -> excursions.forEach(excursion -> {
                        if (excursion.getVacation() == null) {
                            excursion.setVacation(vacation);
                        }
                        // Save excursions
                        excursionRepository.save(excursion);
                    }));
            // Save cart items
            purchase.getCartItems().forEach(cartItem -> {
                cartItem.setCart(savedCart);

                cartItemRepository.save(cartItem);
            });

            purchase.getCartItems().forEach(cartItem -> {
                Set<Excursion> excursionsForCartItem = cartItem.getExcursions();
                if (excursionsForCartItem != null) {
                    excursionsForCartItem.forEach(excursion -> {
                        Excursion persistedExcursion = excursionRepository.findById(excursion.getId()).orElse(null);
                        if (persistedExcursion != null) {
                            persistedExcursion.getCartItems().add(cartItem);
                            excursionRepository.save(persistedExcursion);
                        }
                    });
                }
            });
            Customer customer = purchase.getCustomer();
            customerRepository.save(customer);
        }
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}