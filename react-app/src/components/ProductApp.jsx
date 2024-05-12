import { useEffect, useState } from "react"
import { findAll, update,create, remove} from "../services/ProductService";
/*Para la comunicación entre backend y fronend es necesario importar las funciones*/
import { ProductGrid } from "./ProductGrid";
import { PropTypes } from "prop-types";
import { ProductForm } from "./ProductForm";
//Elemento Padre

export const ProductApp = (title) => {
    /*Hook*/
    const[products, setProducts] = useState([]);

    const[productSelected, setProductSelected] = useState({
        id:0,
        name: '',
        description: '',
        price: ''
    })
    /*Como obtenemos los productos de findAll, esta función es asincrona,
    por lo que la función que lo llame tambien debe serlo. Para ello, creamos
    una función intermedia entra una que sea useEffect, pues una función useEffect
    no puede ser asincrona, ya que por defecto es sincrona*/
    const getProducts = async () => {
        const result = await findAll();
        console.log(result);
        setProducts(result.data._embedded.products);
    }
    
    useEffect(() => {
        getProducts();
        
    }, [])//Listener para inicializar el hook una vez
    //que se crea y solo cuando se crea
    
    /*Un handler puede ser asincrono, pero un useEffect NUNCA puede
    ser asincrono*/
    const handlerAddProduct = async(product) =>{
        if(product.id > 0){
            const response = await update(product);
            console.log(response.data.id)
            setProducts(products.map(prod =>{
                if(prod.id == response.data.id){
                    return {...response.data}
                }
                return prod;
            }));
        }else{
            const response = await create(product);
            console.log(response.data);
            setProducts([...products, {...response.data}]);
        }
    }

    const handlerRemoveProduct = (id) => {
        console.log(id);
        /*Aunque remove es asincrono y al utilizarlo, teoricamente necesita la
        palabra reservada wait, aqui no es muy necesario pues remove no devuelve
        nada por lo que no necesita await al no necesitar transformar nada*/
        remove(id);
        setProducts(products.filter(product => product.id != id))
    }

    const handlerProductSelected = (product) => {
        setProductSelected({...product});
    }

    return (
        <div className="container my-4">
        <h2>Prueba</h2>
        <div className="row">
            <div className="col"><ProductForm 
            handlerAdd={handlerAddProduct}
            productSelected = {productSelected}/></div>
            <div className="col">
            {
               products.length > 0? <ProductGrid products = {products} 
               handlerRemove = {handlerRemoveProduct}
               handlerProductSelected = {handlerProductSelected}/>: <div className="alert alert-warning">
                No hay productos en el sistema
               </div>
            }
            
            </div>
        </div>  
        </div>
    )
    //Pasamos los handler para agregar y eliminar productos
    //Pasamos los products al componente hijo
}

ProductApp.propTypes = {
    title: PropTypes.string.isRequired
}