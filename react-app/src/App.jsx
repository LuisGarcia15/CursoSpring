import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

/*Una expresion lambda en Javaescript siempre
es una constante*/
export const App = () => {
  /*useState
  Permite modificar en timpo real los datos
  ya que es un almacen donde se guarda los datos
  modificados*/
  const [count, setCount] = useState(0)

  /*El código es javascript, aunque parezca
  HTML, es código javascript o una combinación
  de HTML y Javascript*/

  /* <> fragmento*/
  return (
    <>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
      </div>
    </>
  )
}

export default App
