//Elemento Hijo
///Obtiene propiedades del padre como los productos
//Componente padre a la vez
import { PropTypes } from "prop-types";
import { ProductDetail } from "./ProductDetail";
export const ProductGrid = ({handlerProductSelected,handlerRemove, products = []}) => {
    return (
        <table className="table table-hover table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>update</th>
                    <th>remove</th>
                </tr>
            </thead>
            <tbody>
               {products.map(product =>{
                return <ProductDetail 
                handlerRemove={handlerRemove} handlerProductSelected={handlerProductSelected}
                product={product} key={product.id}/>
               })}
            </tbody>
        </table>
    )
}

//Valida que los proptypes sean validos
ProductGrid.propTypes = {
    products: PropTypes.array.isRequired,
    handlerRemove: PropTypes.func.isRequired,
    handlerProductSelected: PropTypes.func.isRequired
}