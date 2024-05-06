import { useEffect, useState } from "react"
import { listProduct } from "../services/ProductService";
import { ProductGrid } from "./ProductGrid";
import { PropTypes } from "prop-types";
//Elemento Padre

export const ProductApp = (title) => {
    /*Hook*/
    const[products, setProducts] = useState([]);
    
    useEffect(() => {
        const result = listProduct();
        setProducts(result);
    }, [])//Listener para inicializar el hook una vez
    //que se crea y solo cuando se crea
    
    return (
        <>
        <h1>{title}</h1>
        <ProductGrid products = {products}/>
        </>
    )
    //Pasamos los products al componente hijo
}

ProductApp.propTypes = {
    title: PropTypes.string.isRequired
}