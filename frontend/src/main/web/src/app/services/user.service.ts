import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from "../entities/user";
import {Observable, of, throwError} from "rxjs";
import {catchError, tap, finalize} from "rxjs/operators";
import {Router} from "@angular/router";
import {UserInvite} from '../entities/user-invite';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  authenticated = localStorage.getItem('user') ? true : false;
  user: User = JSON.parse(localStorage.getItem('user'));

  private userUrl = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient, private router: Router) {
  }

  getToken() {
    return this.user.token;
  }

  getUser(user: User): Observable<User> {
    return this.http.get<User>(this.userUrl + `/get/${user.id}`, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      catchError(this.handleError<any>('getUser'))
    );
  }
  updateUser(user: User) {
    return this.http.put<User>(this.userUrl + '/update', user,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      catchError(this.handleError<any>('signUp'))
    );
  }
  createUser(user: User) {
    return this.http.post<User>(this.userUrl + '/create', user, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      catchError(this.handleError<any>('signUp'))
    );
  }
  deleteUser(user: User) {
    return this.http.delete<User>(this.userUrl + `/delete/${user.id}`,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      catchError(this.handleError<any>('signUp'))
    );
  }
  signUp(user: User): Observable<any> {
    return this.http.post<User>('http://localhost:8080/api/sign-up', user,{
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'json'
    }).pipe(
      catchError(this.handleError<any>('signUp'))
    );

  }

  login(user): Observable<User> {
    return this.http.post<any>('http://localhost:8080/api/login', user).pipe(
      tap(response => {
        this.user = response;
        localStorage.setItem('user', JSON.stringify(response));
        this.authenticated = true}),
      catchError(this.handleError<any>('login'))
    );
  }
  logout() {
    return this.http.post<User>('http://localhost:8080/api/logout', this.user, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(finalize(() => {
      this.authenticated = false; localStorage.removeItem('user'); this.router.navigateByUrl('/login')
    }), catchError(this.handleError<any>('logout')))
  }

  private handleError<T>(operation= 'operation') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }
  searchUsers(roleId: number, term: string): Observable<User[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<User[]>(this.userUrl + `/search/${roleId}/${term}`, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      catchError(error => {return of([])})
    );
  }

  sendUserInvite(userInvite: UserInvite): Observable<UserInvite> {
    console.log("sent invite");
    console.log(userInvite);
    return this.http.post<UserInvite>(this.userUrl + '/invite/send', userInvite, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
        tap(response => {
          console.log(response);
        }),
      catchError(this.handleError<any>('sendUserInvite'))
    );
  }

  getUserInvite(): Observable<UserInvite[]> {
    return this.http.get<UserInvite[]>(this.userUrl + '/invite/' + this.user.id, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      tap(response => {
        console.log(response);
      }),
      catchError(this.handleError<any>('sendUserInvite'))
    );
  }

  acceptUserInvite(id) {
    return this.http.put<UserInvite>(this.userUrl + '/invite/' + id, id, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      tap(response => {
        console.log(response);
      }),
      catchError(this.handleError<any>('acceptUserInvite')))
  }

  declineUserInvite(id) {
    return this.http.delete<UserInvite>(this.userUrl + '/invite/' + id, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      tap(response => {
        console.log(response);
      }),
      catchError(this.handleError<any>('declineUserInvite')))
  }

  getFriendsList(): Observable<UserInvite[]> {
    return this.http.get<UserInvite[]>(this.userUrl + '/invite/friends/' + this.user.id, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      tap(response => {
        console.log(response);
      }),
      catchError(this.handleError<any>('getFriendsList'))
    );
  }

  deleteFriendFromList(id) {
    return this.http.delete<UserInvite>(this.userUrl + '/invite/friends/'+ this.user.id + '/' + id, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      tap(response => {
        console.log(response);
      }),
      catchError(this.handleError<any>('deleteFriendFromList')))
  }

  setStatus(stausId, userId) {
    return this.http.post(this.userUrl + `/status/${stausId}/${userId}`, undefined, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      tap(response => {
      }),
      catchError(this.handleError<any>('sendUserInvite'))
    );

  }
}
