export enum Role {
    USER = 'USER',
    ADMIN = 'ADMIN'
}

export interface User {
    id: number;
    email: string;
    username: string;
    role: Role;
}
