import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'
import FormLogin from './Components/Login/FormLogin'
import Login from './Components/Login/Login'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import ForgotPasswordForm from './Components/Login/ForgotPasswordForm'
import BasePassword from './Components/Login/BasePassword'
import EmailSent from './Components/Login/EmailSent'
import Dashboard from './Components/Layout/Content/Dashboard'

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />
  },
  {
    path: "/register",
    element: <FormLogin />
  },
  {
    path: "/password",
    element: <BasePassword />,
    children: [
      {path: "/password/valide",
      element: <EmailSent />},
      {path: "/password",
      element: <ForgotPasswordForm />},
    ]
  },
  {
    path: "/support",
    element: <Dashboard />
  },
  {
    path: "/pruebas",
    element: <App />
  },
]);

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>,
)