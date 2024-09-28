# 著者

## 作成(POST /author)

### request

```shell
curl -X POST\
     -H 'content-type: application/json'\
     -d '{"name": "Martin Fowler", "birthday": "1963-12-18"}'\
     http://localhost:8080/author
```

### response

```json
{
  "id": "4825bb63-7dcb-11ef-bf35-c55b73d2b624",
  "name": "Martin Fowler",
  "birthday": "1963-12-18"
}
```

## 一覧(GET /author)

### request

```shell
curl http://localhost:8080/author
```

### response

```json
[
  {
    "id": "fbeb2343-79b4-11ef-a0bc-439044e7dc1e",
    "name": "bbbbbb",
    "birthday": null
  },
  {
    "id": "4825bb63-7dcb-11ef-bf35-c55b73d2b624",
    "name": "Martin Fowler",
    "birthday": "1963-12-18"
  }
]
```

## 取得(GET /author/[id])

### request

```shell
curl http://localhost:8080/author/4825bb63-7dcb-11ef-bf35-c55b73d2b624
```

### response

```json
{
  "id": "4825bb63-7dcb-11ef-bf35-c55b73d2b624",
  "name": "Martin Fowler",
  "birthday": "1963-12-18"
}
```

## 更新(PUT /author/[id])

### request

```shell
curl -X PUT\
     -H 'content-type: application/json'\
     -d '{"name": "Kent Beck", "birthday": "1961-03-31"}'\
     http://localhost:8080/author/fbeb2343-79b4-11ef-a0bc-439044e7dc1e
```

### response

```json
{
  "id": "fbeb2343-79b4-11ef-a0bc-439044e7dc1e",
  "name": "Kent Beck",
  "birthday": "1961-03-31"
}
```

# 書籍

## 作成(POST /book)

### request

```shell
curl -X POST\
     -H 'content-type: application/json'\
     -d '{
            "title": "Patterns of Enterprise Application Architecture", 
            "price": 10322,
            "publicationStatus": "PUBLISHED",
            "authorIds": ["4825bb63-7dcb-11ef-bf35-c55b73d2b624"]
          }'\
     http://localhost:8080/book
```

### response

```json
{
  "id": "e75a74e4-7dcc-11ef-bf35-e5303b98f2be",
  "title": "Patterns of Enterprise Application Architecture",
  "price": 10322,
  "publicationStatus": "PUBLISHED",
  "authorIds": [
    "4825bb63-7dcb-11ef-bf35-c55b73d2b624"
  ]
}
```

## 更新(PUT /book/[id])

### request

```shell
curl -X PUT\
     -H 'content-type: application/json'\
     -d '{
            "title": "Patterns of Enterprise Application Architecture", 
            "price": 5013,
            "publicationStatus": "PUBLISHED",
            "authorIds": ["4825bb63-7dcb-11ef-bf35-c55b73d2b624"]
          }'\
     http://localhost:8080/book/e75a74e4-7dcc-11ef-bf35-e5303b98f2be
```

### response

```json
{
  "id": "e75a74e4-7dcc-11ef-bf35-e5303b98f2be",
  "title": "Patterns of Enterprise Application Architecture",
  "price": 5013,
  "publicationStatus": "PUBLISHED",
  "authorIds": [
    "4825bb63-7dcb-11ef-bf35-c55b73d2b624"
  ]
}
```

## 一覧(GET /book)

### request

```shell
curl http://localhost:8080/book
```

### response

```json
[
  {
    "id": "bf6b34ae-7a49-11ef-bbcc-c95c3d13914a",
    "title": "Test Driven Development",
    "price": 999999,
    "publicationStatus": "UNPUBLISHED",
    "authorIds": [
      "fbeb2343-79b4-11ef-a0bc-439044e7dc1e"
    ]
  },
  {
    "id": "25012ca2-7a79-11ef-965e-57645e936e49",
    "title": "SQL Anti Patterns",
    "price": 999999,
    "publicationStatus": "UNPUBLISHED",
    "authorIds": [
      "8488d6f1-7a74-11ef-965e-8f49577d5a37"
    ]
  },
  {
    "id": "e75a74e4-7dcc-11ef-bf35-e5303b98f2be",
    "title": "Patterns of Enterprise Application Architecture",
    "price": 5013,
    "publicationStatus": "PUBLISHED",
    "authorIds": [
      "4825bb63-7dcb-11ef-bf35-c55b73d2b624"
    ]
  }
]
```

## 取得(GET /book/[id])

### request

```shell
curl http://localhost:8080/book/e75a74e4-7dcc-11ef-bf35-e5303b98f2be
```

### response

```json
{
  "id": "e75a74e4-7dcc-11ef-bf35-e5303b98f2be",
  "title": "Patterns of Enterprise Application Architecture",
  "price": 5013,
  "publicationStatus": "PUBLISHED",
  "authorIds": [
    "4825bb63-7dcb-11ef-bf35-c55b73d2b624"
  ]
}
```

## 著者で検索(GET /book?authorId=[著者のID])

### request

```shell
curl http://localhost:8080/book?authorId=4825bb63-7dcb-11ef-bf35-c55b73d2b624
```

### response

```json
[
  {
    "id": "e75a74e4-7dcc-11ef-bf35-e5303b98f2be",
    "title": "Patterns of Enterprise Application Architecture",
    "price": 5013,
    "publicationStatus": "PUBLISHED",
    "authorIds": [
      "4825bb63-7dcb-11ef-bf35-c55b73d2b624"
    ]
  }
]
```

