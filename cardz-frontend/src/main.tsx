import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { BrowserRouter, Route, Routes } from 'react-router'
import Login from './pages/Login.tsx'
import Dashboard from './pages/Dashboard.tsx'
import RegisterUser from './pages/RegisterUser.tsx'
import { PrivateRoute } from './components/PrivateRoute.tsx'
import DeckPage from './pages/DeckPage.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <BrowserRouter>
        <Routes>
          <Route index element={<Login />} />
          <Route path="/register" element={<RegisterUser />} />
          <Route element={<PrivateRoute />}>
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/deck/:deckId" element={<DeckPage />} />
          </Route>
        </Routes>
      </BrowserRouter>
  </StrictMode>,
)
