import { Input, Component, OnInit, OnChanges } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit, OnChanges {

  displayURL;
  
  constructor(private sanitizer: DomSanitizer) {
    this.displayURL = this.getSantizeUrl('https://www.youtube.com/embed/cRy5_KpPxyM');

  }

  ngOnChanges() {
    this.displayURL = this.getSantizeUrl('https://www.youtube.com/embed/cRy5_KpPxyM');

  }
  sanitizedDisplayUrl() {
    return this.getSantizeUrl('https://www.youtube.com/embed/cRy5_KpPxyM');
  }

  public getSantizeUrl(url: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  ngOnInit(): void {
  }

}
