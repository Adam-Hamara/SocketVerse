import { Component, HostListener, OnInit, Pipe, PipeTransform } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ProfileUser } from '../../models/user-profile';
import { Chat, Message } from '../../models/chat';
import { Router, RouterLinkActive } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { UsersService } from '../../services/user.service';
import { ChatsService } from '../../services/chat.service';

@Component({
  selector: 'app-chat-app',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    RouterLinkActive
  ],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {
  searchText: string = '';
  selectedChat: Chat | null = null;
  localUser: ProfileUser | null = null;

  messages: Message[] = [];

  filteredUsers: ProfileUser[] = [];
  userss: ProfileUser[] = [];

  chats: Chat[] = [];

  isSettingsOpen = false;
  isFilteredUsersShown = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private keycloakService: KeycloakService,
    private usersService: UsersService,
    private chatsService: ChatsService
  ){
    this.getLocalUser();
  }

  sendMessageForm = this.formBuilder.group({
    message: ''
  });


  goToHomePage(){
    this.router.navigate(['/home']);
  }

  selectChat(chat: Chat) {
    this.selectedChat = chat;
    this.messages.push({
      text: '<switching to new chat>',
      senderId: chat.uniqueId!,
      sentDate: new Date(Date.now())
    });
  }

  sendMessage() {
    const message = this.sendMessageForm.value.message ?? "";
    // console.log("Message: ", message)
    if(message.trim() && message.length > 0){
      this.messages.push({
        text: message,
        senderId: this.localUser?.unique_id!,
        sentDate: new Date(Date.now())
      });
      this.sendMessageForm.reset();
    }
  }

  initials(user: ProfileUser): string {
    if (!user.username) return '';
    return user.username
      .split(' ')
      .map(part => part[0])
      .join('')
      .toUpperCase();
  }
  
  toggleSettings(): void {
    this.isSettingsOpen = !this.isSettingsOpen;
  }

  closeSettings(): void {
    this.isSettingsOpen = false;
  }

  @HostListener('document:click', ['$event.target'])
  onClickOutside(targetElement: HTMLElement) {
    const clickedInsideSettings = targetElement.closest('.settings-menu');
    if (!clickedInsideSettings) {
      this.closeSettings();
    }
    
    const clickedInsideSearchBar = targetElement.closest('.search-wrapper');
    if (!clickedInsideSearchBar) {
      this.isFilteredUsersShown = false;
    }
    else{
      this.isFilteredUsersShown = true;
    }
  }

  @HostListener('document:keydown.escape')
  onEscape() {
    this.closeSettings();
  }

  onLogout(): void {
    this.keycloakService.logout('http://localhost:4200');
    this.closeSettings();
  }

  getLocalUser(){
    const token = this.keycloakService.getKeycloakInstance().tokenParsed;
    const email = token!['email'];

    this.usersService.currentUserProfile(email).subscribe({ 
      next: (response) => {
        if(response === null){
          this.addNewUser()
        }
        else{
          this.localUser = response
          this.getAllChats()
        }
      },
      error: (err) => {
        console.error('Error occurred:', err);
      }
    });
  }

  addNewUser(){
    const token = this.keycloakService.getKeycloakInstance().tokenParsed;
    const email = token!['email'];
    const username = token!['preferred_username'];
    const firstname = token!['given_name'];
    const lastname = token!['family_name'];

    const user: ProfileUser = {
      email: email,
      username: username,
      firstname: firstname,
      lastname: lastname
    }

    this.usersService.addUser(user).subscribe({ 
      next: (response) => {
        this.getLocalUser();
      },
      error: (err) => {
        console.error('Error occurred:', err);
      }
    });
  }

  onSearchChange() {
    const text = this.searchText.trim().toLowerCase();
    if (text.length === 0) {
      this.filteredUsers = [];
      this.isFilteredUsersShown = false
      return;
    }
    
    this.usersService.allUsers().subscribe({ 
      next: (response) => {
        this.filteredUsers = response
        .filter(u => u.unique_id !== this.localUser!.unique_id!)
        .filter(u => u.username.toLowerCase().includes(text) )
        .filter(u => !this.chats.some(c => c.participants.some(p => p.unique_id === u.unique_id)));
        this.isFilteredUsersShown = true
      },
      error: (err) => {
        console.error('Error occurred:', err);
      }
    });
  }

  getInitials(name: string): string {
    return name
      .split(' ')
      .map(n => n[0])
      .join('')
      .toUpperCase();
  }

  newChatChosen(user: ProfileUser){
    const chat: Chat = {
      type: 'DIRECT',
      participants: [user, this.localUser!]
    }

    this.chatsService.createChat(chat).subscribe({ 
      next: (response) => {
        this.getAllChats()
      },
      error: (err) => {
        console.error('Error occurred:', err);
      }
    });
  }

  getAllChats(){
    this.chatsService.myChats(this.localUser!.unique_id!).subscribe({ 
      next: (response) => {
        this.chats = response
      },
      error: (err) => {
        console.error('Error occurred:', err);
      }
    });
  }

  getSenderFromChat(chat: Chat): ProfileUser{
    return chat.participants.filter(u => u.unique_id !== this.localUser?.unique_id!)[0]
  }
}