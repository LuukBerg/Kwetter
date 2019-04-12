import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Globals } from '../Globals/globals';
import { AuthService } from './authentication.service';
import { callbackify } from 'util';
import { Kweet } from '../_models';
import { Observable, BehaviorSubject } from 'rxjs';
import { take } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService
{
    websocket: WebSocket;
    callback: ((message: Kweet) => any) | null;
    kweet: Kweet;
    obsArray: BehaviorSubject<Kweet>;
    array: Observable<Kweet>;
    constructor(private globals: Globals, private authService : AuthService)
    {
        this.websocket = new WebSocket(this.globals.websocketsurl + 'api/socket/Bearer ' + this.authService.currentUserValue.token);
        this.websocket.onmessage = (event) => this.onMessage(event);
        this.obsArray = new BehaviorSubject<Kweet>(new Kweet);
        this.array = this.obsArray.asObservable();
    
    }

    open(){
        this.websocket.OPEN;
    }
    send(message : string){
        this.websocket.send(message);
    }
    onMessage(event: MessageEvent)
    {
        this.addElementToObsArray(JSON.parse(event.data) as Kweet);
    }
    addElementToObsArray(kweet : Kweet){
        this.array.pipe(take(1)).subscribe(val => {
            this.obsArray.next(kweet);
            console.log("added to obs array: " + kweet);
        })
    }
    close(){
        this.websocket.CLOSED;
    }
}
