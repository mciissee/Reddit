import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Topic, Comment, Vote } from '../models/posts';
import { ConfigService } from './config.service';

@Injectable({providedIn: 'root'})
export class PostService {

    constructor(
        private readonly http: HttpClient,
        private readonly configService: ConfigService
    ) { }

    createTopic(args: {
        title: string, content: string
    }) {
        return this.http.post<Topic>(this.configService.apiUrl + 'topics/', {
            title: args.title,
            content: args.content
        }, {
            headers: new HttpHeaders({
                "Content-Type": "application/json"
            })
        });
    }

    createComment(args: {
        postId: number, content: string
    }) {
        return this.http.post<Comment>(this.configService.apiUrl + 'comments/' + args.postId + '/', {
            content: args.content
        }, {
            headers: new HttpHeaders({"Content-Type": "application/json"})
        });
    }



    listTopics() {
        return this.http.get<Topic[]>(this.configService.apiUrl + 'topics/?size=1000').pipe(
            map((res: any) => res.content)
        );
    }

    listComments(postId: number) {
        return this.http.get<Comment[]>(this.configService.apiUrl + 'comments/' + postId);
    }


    listTopicsOfAuthor(username: string) {
        return this.http.get<Topic[]>(this.configService.apiUrl + `topics/by-author/${username}`).pipe(
            map((res: any) => res.content)
        );
    }

    listCommentsOfAuthor(username: string) {
        return this.http.get<Comment[]>(this.configService.apiUrl + `comments/by-author/${username}`);
    }


    deleteTopic(postId: number) {
        return this.http.delete(this.configService.apiUrl + 'topics/' + postId + '/', {
            responseType: 'text'
        });
    }

    deleteComment(postId: number) {
        return this.http.delete(this.configService.apiUrl + 'comments/' + postId + '/', {
            responseType: 'text'
        });
    }

    voteStatus(postId: number) {
        return this.http.get<Vote>(this.configService.apiUrl + `votes/${postId}/status`, {});
    }

    unvote(postId: number) {
        return this.http.delete(this.configService.apiUrl + `votes/${postId}/unvote`, { responseType: 'text' });
    }

    upvote(postId: number) {
        return this.http.post<Vote>(this.configService.apiUrl + `votes/${postId}/upvote`, {});
    }

    downvote(postId: number) {
        return this.http.post<Vote>(this.configService.apiUrl + `votes/${postId}/downvote`, {});
    }
}
