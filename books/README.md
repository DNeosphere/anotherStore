# anotherStore

# Estas son las api's para el producto de anotherStore:


# Para crear un producto http://localhost:8080/products/create
# Para obtener todos los productos http://localhost:8080/products/all
# Para obtener un producto por id http://localhost:8080/products/{id}
# Para eliminar un producto http://localhost:8080/products/delete/{id}
# Para actualizar un producto http://localhost:8080/products/update/{id}

# Para crear  y actualizar un producto se debe enviar un json con los siguientes campos:
# Enviar los siquientes campos:
# {
    "name":"Iphone 15",
    "description":"Lo ultimo en guaracha!!",
    "price": 10,
    "stock": 10,
    "disponible": true
# }
# todos los campoos son requeridos tanto para crear como para actualizar.
# Las validaciones de cada uno de los campos estan el archivo controller/DTO/ProductDTO