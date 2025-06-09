import { Injectable } from '@angular/core';
import { from, Observable, of, switchMap } from 'rxjs';
import { ProfileUser } from '../models/user-profile';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  
  usersUrl = environment.apiUrl + "/SocketVerse/users"

  constructor(private http: HttpClient) {}

  currentUserProfile(email: string): Observable<ProfileUser | null> {
    return this.http.get<ProfileUser | null>(this.usersUrl + `?email=${email}`);
  }

  allUsers(): Observable<ProfileUser[]> {
    return this.http.get<ProfileUser[]>(this.usersUrl + "/all");
  }

  addUser(user: ProfileUser): Observable<any> {
    return this.http.post<ProfileUser>(this.usersUrl, user);
  }

  updateUser(user: ProfileUser): Observable<any> {
    return this.http.get<ProfileUser>(this.usersUrl);
  }
}