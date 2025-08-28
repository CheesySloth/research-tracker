import { useEffect } from 'react';

const themes = [
  { name: "Midnight Blossom", value: "midnight-blossom" },
  { name: "Pink Mist", value: "pink-mist" },
  { name: "Ocean Horizon", value: "ocean-horizon" },
  { name: "Deep Ocean", value: "deep-ocean" }
];

export default function ThemeSelector() {
  useEffect(() => {
    const savedTheme = localStorage.getItem('theme') || 'ocean-horizon';
    document.documentElement.setAttribute('data-theme', savedTheme);
  }, []);

  const setTheme = (theme) => {
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('theme', theme);
  };

  return (
    <div className="theme-selector">
      <label htmlFor="theme-select">Theme:</label>
      <select 
        id="theme-select"
        onChange={(e) => setTheme(e.target.value)}
        defaultValue={localStorage.getItem('theme') || 'blue-horizon'}
      >
        {themes.map(theme => (
          <option key={theme.value} value={theme.value}>
            {theme.name}
          </option>
        ))}
      </select>
    </div>
  );
};