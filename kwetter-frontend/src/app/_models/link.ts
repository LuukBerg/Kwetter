export type LinkMap = { [rel: string]: string };

export class Link {
    url : string;
    rel : string;

    static asMap(items: Link[]): LinkMap
    {
        let result: LinkMap = {};
        for (let i = 0; i < items.length; i += 1) {
            let item = items[i];
            result[item.rel] = item.url;
        }
        return result;
    }
}
