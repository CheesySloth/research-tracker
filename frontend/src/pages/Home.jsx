import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import SearchBar from '../components/SearchBar';
import NoteCard from '../components/NoteCard';

export default function Home() {
  const [notes, setNotes] = useState([]);
  const navigate = useNavigate();

  return (
    <div className="home-page">
      <SearchBar onSearch={setNotes} />
      <div className="notes-grid">
        {notes.map(note => (
          <NoteCard 
            key={note.id}
            note={note}
            onClick={() => navigate(`/preview/${note.id}`)}
          />
        ))}
      </div>
    </div>
  );
}