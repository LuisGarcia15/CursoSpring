//Componente hijo
import { PropTypes } from "prop-types";
//Props [handlerRemove, product]
export const ProductDetail = ({handlerProductSelected, handlerRemove, product = {}}) => {
    return(
        <tr key = {product.name}>
                    <td>{product.name}</td>
                    <td>{product.price}</td>
                    <td>{product.description}</td>
                    <td>
                    <button onClick={() => handlerProductSelected(
                        product
                        )}>
                            update
                        </button>
                    </td>
                    <td>
                        <button onClick={() => handlerRemove(product.name)}>
                            remove
                        </button>
                    </td>
                </tr>
    )
}

//Verificando las propiedades pasadas de padre a hijo
ProductDetail.propTypes = {
    product: PropTypes.object.isRequired,
    handlerRemove: PropTypes.func.isRequired,
    handlerProductSelected: PropTypes.func.isRequired
}

