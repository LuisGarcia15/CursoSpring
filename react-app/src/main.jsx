import React from 'react'
import ReactDOM from 'react-dom/client'
//import {App} from './App.jsx'
/*Le colocamos {} ya que es una función Lamda*/
//import './index.css'
import { ProductApp } from './components/ProductApp.jsx'

//Componente padre de todos los componentes
ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ProductApp title= {'Lista de productos!!'}/>
  </React.StrictMode>,
)
