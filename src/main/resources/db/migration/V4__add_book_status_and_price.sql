CREATE TYPE publication_status AS ENUM ('unpublished', 'published');

ALTER TABLE books
    /*
    既にDB内に価格が設定されていない書籍が存在する可能性があり、その価格をデフォルトで0にしてしまうと0円で購入できてしまうので、
    デフォルト値は購入できないくらい十分大きな値にしておく。
    多少不自然かもしれないが、安全側に倒す。
    */
    ADD COLUMN price              INT                NOT NULL DEFAULT 999999,
    ADD COLUMN publication_status publication_status NOT NULL DEFAULT 'unpublished';
