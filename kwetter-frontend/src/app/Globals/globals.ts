import { Injectable } from '@angular/core';

@Injectable()
export class Globals {
  baseurl: string = 'http://localhost:18080/Kwetter/api/'
  websocketsurl: string = 'ws://localhost:18080/Kwetter/'
}