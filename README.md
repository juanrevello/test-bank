# test-bank

API con arquitectura tipo hexagonal que simula un pequeño banco, con usuarios, clientes y transferencias.

## Ejecución

Corre en localhost:8080 por defecto, y utiliza una base de datos en memoria H2.

## CURLs

### Create User

```
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "juan@mail.com",
    "password": "root"
}'
```

### Create Wallet

```
curl --location 'http://localhost:8080/wallets' \
--header 'Content-Type: application/json' \
--data '{
    "user_id": 1
}'
```

### New Deposit

```
curl --location 'http://localhost:8080/wallets/deposit' \
--header 'Content-Type: application/json' \
--data '{
    "destination_wallet_id": 1,
    "amount": 100
}'
```

### New Transfer

```
curl --location 'http://localhost:8080/wallets/transfer' \
--header 'Content-Type: application/json' \
--data '{
    "origin_wallet_id": 1,
    "destination_wallet_id": 2,
    "amount": 10
}'
```

### Get User

```
curl --location 'http://localhost:8080/users/1'
```

### Get Wallet with Transfers

```
curl --location 'http://localhost:8080/wallets/1'
```

## Consideraciones

Quedan pendientes de testear algunas clases, así cómo también implementar la lógica de manejo de excepciones.
Además se podrían aplicar Transaccions cuando se accede a la base de datos, para mantener la atomicidad de las operaciones.