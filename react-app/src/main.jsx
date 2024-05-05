import React from 'react'
import ReactDOM from 'react-dom/client'
import {App} from './App.jsx'
/*Le colocamos {} ya que es una función Lamda*/
import './index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
