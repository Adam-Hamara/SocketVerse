import { Injectable } from '@angular/core';
import { concatMap, from, map, Observable, take, tap } from 'rxjs';
import { Chat, Message } from '../models/chat';
import { ProfileUser } from '../models/user-profile';
import { UsersService } from './user.service';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ChatsService {
  chatsUrl = environment.apiUrl + "/SocketVerse/chats"
  
  constructor(private http: HttpClient) {}

  myChats(userUniqueId: string): Observable<Chat[]> {
    return this.http.get<Chat[]>(this.chatsUrl + `/${userUniqueId}`);
  }

  createChat(chat: Chat): Observable<Chat[]> {
    return this.http.post<Chat[]>(this.chatsUrl, chat);
  }

  isExistingChat(otherUserId: string): Observable<string | null> {
    return new Observable();
  }

  addChatMessage(chatId: string, message: string): Observable<any> {
    return new Observable();
  }

  getChatMessages$(chatId: string): Observable<Message[]> {
    return new Observable();
  }

  // addChatNameAndPic(currentUserId: string | undefined, chats: Chat[]): Chat[] {
  //   chats.forEach((chat: Chat) => {
  //     const otherUserIndex =
  //       chat.userIds.indexOf(currentUserId ?? '') === 0 ? 1 : 0;
  //     const { username, avatarUrl } = chat.users[otherUserIndex];
  //     chat.chatName = username;
  //     chat.chatPic = avatarUrl;
  //   });

  //   return chats;
  // }
}
