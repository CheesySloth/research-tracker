import { Outlet } from 'react-router-dom'
import ThemeSelector from './ThemeSelector'

export default function Layout() {
  return (
    <div className="app-container">
      <header className='app-header'>
        <h1>Research Tracker</h1>
        <ThemeSelector />
      </header>
      <main>
        <Outlet /> {/* This renders child routes */}
      </main>
    </div>
  )
};