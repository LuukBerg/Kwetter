import { Injectable } from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class HttpWrapService implements HttpInterceptor{
  intercept(req: HttpRequest<any>, next: HttpHandler) {
		let clone = req.clone({ headers: new HttpHeaders({'Auth-Ding': 'bla' })});
		return next.handle(clone);
	}
}
