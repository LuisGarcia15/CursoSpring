import { useEffect, useState } from "react"
import { listProduct } from "../services/ProductService";
import { ProductGrid } from "./ProductGrid";
import { PropTypes } from "prop-types";
import { ProductForm } from "./ProductForm";
//Elemento Padre

export const ProductApp = (title) => {
    /*Hook*/
    const[products, setProducts] = useState([]);
    
    useEffect(() => {
        const result = listProduct();
        setProducts(result);
    }, [])//Listener para inicializar el hook una vez
    //que se crea y solo cuando se crea
    
    const handlerAddProduct = (product) =>{
        console.log(product)
        setProducts([...products, {...product}]);
    }

    return (
        <div>
        <h1>Error</h1>
        <div>
            <div><ProductForm handlerAdd={handlerAddProduct}/></div>
            <div><ProductGrid products = {products}/></div>
        </div>  
        </div>
    )
    //Pasamos los products al componente hijo
}

ProductApp.propTypes = {
    title: PropTypes.string.isRequired
}