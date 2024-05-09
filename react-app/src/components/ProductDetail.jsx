//Componente hijo
import { PropTypes } from "prop-types";
//Props [handlerRemove, product]
export const ProductDetail = ({handlerProductSelected, handlerRemove, product = {}}) => {
    return(
        <tr key = {product.id}>
                    <td>{product.id}</td>
                    <td>{product.name}</td>
                    <td>{product.price}</td>
                    <td>{product.description}</td>
                    <td>
                    <button className="btn btn-secondary btn-sm" onClick={() => handlerProductSelected(
                        product
                        )}>
                            update
                        </button>
                    </td>
                    <td>
                        <button className="btn btn-danger btn-sm" onClick={() => handlerRemove(product.id)}>
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

