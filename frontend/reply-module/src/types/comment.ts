import { User } from "./user";

export interface Comment {
  id: number;
  content: string;
  user: User;
  likingUsers: User[];
  createdDate: string;
  modifiedDate: string;
}

export interface GuestUserInfo {
  guestUserId?: number;
  guestNickName?: string;
  guestUserPassword?: string;
}

export interface ScriptInfo {
  url: string | null;
  projectSecretKey: string | null;
}

export interface CreateCommentRequestData extends Omit<GuestUserInfo, "guestUserId">, ScriptInfo {
  content: string;
}

export type GetCommentsRequestParams = ScriptInfo;

export type GetProjectRequestParams = Pick<ScriptInfo, "projectSecretKey">;

export type EditCommentRequestData = Pick<Comment, "content"> & Omit<GuestUserInfo, "guestNickName">;

export type EditCommentParameter = Pick<Comment, "id" | "content"> & Omit<GuestUserInfo, "guestNickName">;

export type DeleteCommentRequestParameter = Pick<Comment, "id"> & Omit<GuestUserInfo, "guestNickName">;

export type LikeCommentParameter = { user: User | undefined; commentId: Comment["id"] };
