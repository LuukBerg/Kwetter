import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Globals } from '../Globals/globals';
import { AuthService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService
{
    websocket: WebSocket;
    callback: ((message: any) => any) | null;

    constructor(private globals: Globals, private authService : AuthService)
    {
        this.websocket = new WebSocket(this.globals.websocketsurl + 'api/socket/Bearer ' + this.authService.currentUserValue.token);
        this.websocket.onmessage = (event) => this.onMessage(event);
    
    }

    open(){
        this.websocket.OPEN;
    }
    send(message : string){
        this.websocket.send(message);
    }
    onMessage(event: MessageEvent)
    {
        console.log(event.data);
    }
}
