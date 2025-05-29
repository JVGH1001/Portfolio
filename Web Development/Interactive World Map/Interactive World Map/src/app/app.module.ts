import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app_routing.module';
import {AppComponent} from './app.component';
import {WorldbankApiService} from './worldbank_api.service';
import {WorldMapComponent} from './components/world_map/world_map.component';
import {CountryInfoComponent} from './components/country_info/country_info.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    WorldMapComponent,
    CountryInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
