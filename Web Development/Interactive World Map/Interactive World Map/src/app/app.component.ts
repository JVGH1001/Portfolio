import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'interactive_world_map';

  selectedSvgId: string = "";
  selectedCapital: string = "";
  selectedRegion: string = "";
  selectedIncomeLevel: string = "";
  selectedLongitude: string = "";
  selectedLatitude: string = "";

onSvgIdSelected(svgId: string) {
  this.selectedSvgId = svgId;
}

onCapitalSelected(capital: string) {
  this.selectedCapital = capital;
}

onRegionSelected(region: string) {
  this.selectedRegion = region;
}

onIncomeSelected(income: string) {
  this.selectedIncomeLevel = income;
}

onLongitudeSelected(longitude: string) {
  this.selectedLongitude = longitude;
}

onLatitudeSelected(latitude: string) {
  this.selectedLatitude = latitude;
}
}