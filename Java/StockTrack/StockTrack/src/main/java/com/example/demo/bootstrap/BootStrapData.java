package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    private final InhousePartRepository inhousePartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository, InhousePartRepository inhousePartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
        this.inhousePartRepository=inhousePartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */

        partRepository.deleteAll();
        productRepository.deleteAll();
        outsourcedPartRepository.deleteAll();
        inhousePartRepository.deleteAll();

        if (inhousePartRepository.count() == 0) {
            InhousePart leftClick = new InhousePart();
            leftClick.setName("Left Click");
            leftClick.setInv(90);
            leftClick.setPrice(3.0);
            leftClick.setId(100L);
            leftClick.setMinimum(10);
            leftClick.setMaximum(100);
            inhousePartRepository.save(leftClick);



            InhousePart rightClick = new InhousePart();
            rightClick.setName("Right Click");
            rightClick.setInv(90);
            rightClick.setPrice(3.0);
            rightClick.setId(101L);
            rightClick.setMinimum(10);
            rightClick.setMaximum(100);
            inhousePartRepository.save(rightClick);

            InhousePart keys = new InhousePart();
            keys.setName("Keys");
            keys.setInv(100);
            keys.setPrice(15.0);
            keys.setId(102L);
            keys.setMinimum(10);
            keys.setMaximum(100);
            inhousePartRepository.save(keys);

            InhousePart screwPack = new InhousePart();
            screwPack.setName("Screw Pack");
            screwPack.setInv(100);
            screwPack.setPrice(3.0);
            screwPack.setId(103L);
            screwPack.setMinimum(10);
            screwPack.setMaximum(100);
            inhousePartRepository.save(screwPack);

            InhousePart laptopLid = new InhousePart();
            laptopLid.setName("Laptop Lid");
            laptopLid.setInv(43);
            laptopLid.setPrice(20.0);
            laptopLid.setId(104L);
            laptopLid.setMinimum(10);
            laptopLid.setMaximum(100);
            inhousePartRepository.save(laptopLid);

            InhousePart chassis = new InhousePart();
            chassis.setName("Chassis");
            chassis.setInv(47);
            chassis.setPrice(24.0);
            chassis.setId(105L);
            chassis.setMinimum(10);
            chassis.setMaximum(100);
            inhousePartRepository.save(chassis);
        }

        if (outsourcedPartRepository.count() == 0) {
            OutsourcedPart cpu = new OutsourcedPart();
            cpu.setCompanyName("AMD");
            cpu.setName("CPU");
            cpu.setInv(23);
            cpu.setPrice(250.0);
            cpu.setId(106L);
            cpu.setMinimum(10);
            cpu.setMaximum(100);
            outsourcedPartRepository.save(cpu);

            OutsourcedPart ram = new OutsourcedPart();
            ram.setCompanyName("Samsung");
            ram.setName("RAM");
            ram.setInv(56);
            ram.setPrice(120.0);
            ram.setId(107L);
            ram.setMinimum(10);
            ram.setMaximum(100);
            outsourcedPartRepository.save(ram);

            OutsourcedPart ssd = new OutsourcedPart();
            ssd.setCompanyName("Samsung");
            ssd.setName("SSD");
            ssd.setInv(72);
            ssd.setPrice(150.0);
            ssd.setId(108L);
            ssd.setMinimum(10);
            ssd.setMaximum(100);
            outsourcedPartRepository.save(ssd);

            OutsourcedPart motherBoard = new OutsourcedPart();
            motherBoard.setCompanyName("MSI");
            motherBoard.setName("Mother Board");
            motherBoard.setInv(17);
            motherBoard.setPrice(300.0);
            motherBoard.setId(109L);
            motherBoard.setMinimum(10);
            motherBoard.setMaximum(100);
            outsourcedPartRepository.save(motherBoard);

            OutsourcedPart screen = new OutsourcedPart();
            screen.setCompanyName("LG");
            screen.setName("Screen");
            screen.setInv(33);
            screen.setPrice(110.0);
            screen.setId(110L);
            screen.setMinimum(10);
            screen.setMaximum(100);
            outsourcedPartRepository.save(screen);

            OutsourcedPart trackPad = new OutsourcedPart();
            trackPad.setCompanyName("IBM");
            trackPad.setName("Track Pad");
            trackPad.setInv(29);
            trackPad.setPrice(65.0);
            trackPad.setId(111L);
            trackPad.setMinimum(10);
            trackPad.setMaximum(100);
            outsourcedPartRepository.save(trackPad);
        }

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

        if (productRepository.count() == 0) {
            Product laptop = new Product("laptop", 1063.0, 16, 0);
            Product keyboard = new Product("keyboard", 24.0, 47, 0);
            Product trackpadAssembly = new Product("trackpad assembly", 70.0, 12, 0);
            Product charger= new Product("charger", 16.0, 25, 0);
            Product screenAssembly= new Product("screen assembly", 130.0, 15, 0);
            productRepository.save(laptop);
            productRepository.save(keyboard);
            productRepository.save(trackpadAssembly);
            productRepository.save(charger);
            productRepository.save(screenAssembly);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
