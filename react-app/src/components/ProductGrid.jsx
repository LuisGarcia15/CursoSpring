//Elemento Hijo
///Obtiene propiedades del padre como los productos
//Componente padre a la vez
import { PropTypes } from "prop-types";
import { ProductDetail } from "./ProductDetail";
export const ProductGrid = ({products = []}) => {
    return (
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
                return <ProductDetail product={product} key={product.name}/>
               })}
            </tbody>
        </table>
    )
}

ProductGrid.propTypes = {
    products: PropTypes.array.isRequired
}