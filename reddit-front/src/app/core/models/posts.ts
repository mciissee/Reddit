interface Post {
    id: number;
    text: string;
    date: number;
    author: string;
    upvotes: {
        count: number;
        active: boolean;
    };
    downvotes: {
        count: number;
        active: boolean;
    };
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
