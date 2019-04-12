import { Details } from './details';
import { Link } from '.';

export class Profile{
    id : number;
    username : string;
    details : Details;
    email : string;
    links : Link[];
}