import { ProfileUser } from './user-profile';

export interface Chat {
  uniqueId?: string;
  type: string;
  name?: string;
  participants: ProfileUser[];
  created_at?: string;
  updated_at?: string;
}

export interface Message {
  text: string;
  senderId: string;
  sentDate: Date;
}