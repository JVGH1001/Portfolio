import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app_country_info',
  templateUrl: './country_info.component.html',
  styleUrls: ['./country_info.component.css']
})

export class CountryInfoComponent implements OnInit {
  @Input() countryId!: string;
  @Input() countryCapital!: string;
  @Input() countryRegion!: string;
  @Input() countryIncomeLevel!: string;
  @Input() countryLongitude!: string;
  @Input() countryLatitude!: string;
  
  constructor() { }

  ngOnInit(): void { }
}
