import axios from "axios";

//Se lee de la BD
const initProducts = [
    {
        id:1,
        name: "Monitos Samsung 64",
        price: 500.00,
        description: "Monitor 4k"
    },
    {
        id:2,
        name: "Apple iPhone 15",
        price: 500.00,
        description: "Apple"
    }
];

export const listProduct = () =>{
    return initProducts
}

//axios
/*Herramienta para comunicación entre react y spring.
Los paquetes se pueden visualizar en package.json
Se instala con npm i axios. Revisar documentación*/

const baseUrl = 'http://localhost:8080/products';
//Url base del proyecto spring

export const findAll = async () => {
    //tiene todos los verbos de http, por lo que lo usamos
    //para consultas

    //son consulatas asincronas, pues no estan sincronizadas
    //con el backend. a menos que se use await, palabra reservada
    //que hara algo, solo si el backend hace algo. aún así, findAll
    //como función sigue retornando una promesa del tipo AxiosResponse
    try{
        const response = await axios.get(baseUrl);
        return response
    }catch (error){
        console.log(error);
    }
    return null;
}

export const create = async ({name, description, price}) => {
    //tiene todos los verbos de http, por lo que lo usamos
    //para consultas

    //son consulatas asincronas, pues no estan sincronizadas
    //con el backend. a menos que se use await, palabra reservada
    //que hara algo, solo si el backend hace algo. aún así, findAll
    //como función sigue retornando una promesa del tipo AxiosResponse

    //el await es para esperar la accion del backend, y asi no retorne
    //una promesa
    try{
        const response = await axios.post(baseUrl, {
            name: name,
            description: description,
            price: price
        });
        return response
    }catch (error){
        console.log(error);
    }
    return undefined;
}

export const update = async ({id, name, description, price}) => {
    //tiene todos los verbos de http, por lo que lo usamos
    //para consultas

    //son consulatas asincronas, pues no estan sincronizadas
    //con el backend. a menos que se use await, palabra reservada
    //que hara algo, solo si el backend hace algo. aún así, findAll
    //como función sigue retornando una promesa del tipo AxiosResponse

    //el await es para esperar la accion del backend, y asi no retorne
    //una promesa
    try{
        const response = await axios.put(`${baseUrl}/${id}`, {
            name,
            description,
            price
        });
        return response
    }catch (error){
        console.log(error);
    }
    return undefined;
}

export const remove = async (id) => {
    //tiene todos los verbos de http, por lo que lo usamos
    //para consultas

    //son consulatas asincronas, pues no estan sincronizadas
    //con el backend. a menos que se use await, palabra reservada
    //que hara algo, solo si el backend hace algo. aún así, findAll
    //como función sigue retornando una promesa del tipo AxiosResponse

    //el await es para esperar la accion del backend, y asi no retorne
    //una promesa
    try{
        await axios.delete(`${baseUrl}/${id}`);
    }catch (error){
        console.log(error);
    }
    return undefined;
}