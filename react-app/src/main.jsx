import React from 'react'
import ReactDOM from 'react-dom/client'
import {App} from './App.jsx'
/*Le colocamos {} ya que es una función Lamda*/
//import './index.css'
import { ProductApp } from './components/ProductApp.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ProductApp/>
  </React.StrictMode>,
)
