import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { AuthService } from '../_services/authentication.service';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor{
    
    constructor(private authService : AuthService){}
    
    intercept(request : HttpRequest<any>, next : HttpHandler): Observable<HttpEvent<any>>{
        //adding jwt token
         
        let currentUser = this.authService.currentUserValue;
        if(currentUser && currentUser.token){
            request = request.clone({
                setHeaders : {
                    Authorization: `Bearer ${currentUser.token}`
                }
            });
            console.log(request.headers);
        }
        return next.handle(request);
    }
}