import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './styles/main.scss' // Global styles

// Initialize theme from localStorage or default
const savedTheme = localStorage.getItem('theme') || 'blue-horizon'
document.documentElement.setAttribute('data-theme', savedTheme)

console.log('React version:', React.version)
console.log('React DOM version:', ReactDOM.version)

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
)