export interface Post {
    id: number;
    date: number;
    content: string;
    author: string;
    upvotes: number;
    hotness: number;
    comments: number;
    downvotes: number;
}

export interface Topic extends Post {
    title: string;
}

export interface Comment extends Post {
    parent: {
        id: number;
        type: 'TOPIC' | 'COMMENT';
    };
}

export interface Vote {
    username: string;
    postId: number;
    type: 'UP_VOTE' | 'DOWN_VOTE';
}
