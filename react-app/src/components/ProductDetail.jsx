//Componente hijo
import { PropTypes } from "prop-types";
export const ProductDetail = ({product = {}}) => {
    return(
        <tr key = {product.name}>
                    <td>{product.name}</td>
                    <td>{product.price}</td>
                    <td>{product.description}</td>
                </tr>
    )
}

//Verificando las propiedades pasadas de padre a hijo
ProductDetail.propTypes = {
    product: PropTypes.object.isRequired
}

