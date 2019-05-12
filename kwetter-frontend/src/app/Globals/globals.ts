import { Injectable } from '@angular/core';

@Injectable()
export class Globals {
  baseurl: string = 'http://localhost:8080/api/'
  websocketsurl: string = 'ws://localhost:8080/Kwetter/'
}