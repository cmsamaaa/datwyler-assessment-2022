import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-language',
  templateUrl: './language.component.html',
  styleUrls: []
})
export class LanguageComponent implements OnInit {

  lang: string = '';

  constructor() { }

  ngOnInit(): void {
    this.lang = localStorage.getItem('lang') || 'en';
  }

  changeLang(lang) {
    localStorage.setItem('lang', lang);
    window.location.reload();
  }

}
