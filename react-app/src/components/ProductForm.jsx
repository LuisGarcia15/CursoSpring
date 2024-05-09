import { useEffect, useState } from "react"

const initialDataForm = {
    id:0,
    name: '',
    description: '',
    price: ''
}
//export const para poder importar el componente que otro lo necesita
export const ProductForm = ({productSelected,handlerAdd}) => {
    //es la variable y una funcion para afectar la variable
    //Siempre debe tener una data inicial el useState
    const [form, setForm] = useState(initialDataForm);
    //userState() maneja una fuente unica de data
    //setForm() modifica el estado que tenga el formulario

    //Los valores de cada variable listado, se obtiene de
    //form. Osea el name se obtiene de form y se asigna
    //a la const name
    const{id, name, description, price} = form;

    useEffect(() => {
        setForm(productSelected);
    }, [productSelected])

    return(
        <form onSubmit={(event) => {
            //PreventDefault
            //Permite, al hacer un submit, los datos
            /*No se pierdan haciendo un refresh*/
            event.preventDefault();
            if(!name || !description || !price){
                alert('Error de datos')
                //return;
            }
            //console.log(form);
            //Limpiando los datos, colocando los datos
            //por defecto
            handlerAdd(form);
            setForm(initialDataForm);
        }}>
            <div>
            <input placeholder="Name"
            className="form-control my-3 w-75"
            name="name"
            value={name}
            onChange={(event) => setForm({
                ...form,
                name: event.target.value
            })}
            />
            </div>
            <div>
            <input placeholder="Price"
            className="form-control my-3 w-75" 
            name="price"
            value={price}
            onChange={(event) => setForm({
                ...form, price: event.target.value
            })}
            />
            </div>
            <div>
            <input placeholder="Description"
            className="form-control my-3 w-75" 
            name="description"
            value={description}
            onChange={(event) => setForm({
                ...form, description: event.target.value
            })}
            />
            </div>
            <div>
            <button type="submit" className="btn btn-primary">
                {id> 0? 'Update':'Create'}
            </button>
            </div>
        </form>
    )
}