import { useEffect, useState } from "react"
import { listProduct } from "../services/ProductService";


export const ProductApp = () => {
    /*Hook*/
    const[products, setProducts] = useState([]);
    
    useEffect(() => {
        const result = listProduct();
        setProducts(result);
    }, [])//Listener para inicializar el hook una vez
    //que se crea y solo cuando se crea
    
    return (
        <>
        <h1>Hola mundo React</h1>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
               {products.map(product =>{
                return (<tr key = {product.name}>
                    <td>{product.name}</td>
                    <td>{product.price}</td>
                    <td>{product.description}</td>
                </tr>)
               })}
            </tbody>
        </table>
        </>
    )
}