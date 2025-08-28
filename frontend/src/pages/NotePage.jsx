import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import MarkdownPreview from '../components/MarkdownPreview';

export default function NotePage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [note, setNote] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [isSaving, setIsSaving] = useState(false);

  // Fetch note data from API
  useEffect(() => {
    const fetchNote = async () => {
      try {
        setIsLoading(true);
        const response = await axios.get(`http://localhost:8080/api/notes/${id}`);
        setNote(response.data);
      } catch (error) {
        console.error('Error fetching note:', error);
        setNote(null);
      } finally {
        setIsLoading(false);
      }
    };

    if (id) {
      fetchNote();
    }
  }, [id]);

  // Handle save to backend
  const handleSave = async () => {
    try {
      setIsSaving(true);
      await axios.put(`http://localhost:8080/api/notes/${id}`, note);
      setIsEditing(false); // Exit edit mode after successful save
      // Optional: Show success message
    } catch (error) {
      console.error('Error saving note:', error);
      // Optional: Show error message
    } finally {
      setIsSaving(false);
    }
  };

  // Handle input changes
  const handleInputChange = (field, value) => {
    setNote(prev => ({ ...prev, [field]: value }));
  };

  if (isLoading) {
    return <div className="app-container">Loading...</div>;
  }

  if (!note) {
    return <div className="app-container">Note not found</div>;
  }

  return (
    <div className="app-container">
      <header className="note-header">
        
        <div className="note-header-center">
          {isEditing ? (
            <input
              type="text"
              value={note.title || ''}
              onChange={(e) => handleInputChange('title', e.target.value)}
              placeholder="Note title"
              className="note-title-input"
            />
          ) : (
            <span className="note-title">{note.title || 'Untitled Note'}</span>
          )}
          <span className="note-author">
            &nbsp;by <span className="note-author-name">{note.author || 'Unknown'}</span>
          </span>
        </div>
        {/* Empty div to balance flex space */}
        <div style={{ width: '60px' }} />
</header>
      
      <main className="note-container">
        {isEditing ? (
          <textarea 
            value={note.content || ''}
            onChange={(e) => handleInputChange('content', e.target.value)}
            placeholder="Write your note content here..."
            className="note-editor"
          />
        ) : (
          <MarkdownPreview content={note.content} />
        )}
        
        {/* Additional fields for editing */}
        {isEditing && (
          <div className="note-metadata">
            <input
              type="text"
              value={note.author || ''}
              onChange={(e) => handleInputChange('author', e.target.value)}
              placeholder="Author"
            />
            <input
              type="text"
              value={note.tags || ''}
              onChange={(e) => handleInputChange('tags', e.target.value)}
              placeholder="Tags (comma separated)"
            />
            <input
              type="text"
              value={note.category || ''}
              onChange={(e) => handleInputChange('category', e.target.value)}
              placeholder="Category"
            />
          </div>
        )}
        
        <div className="note-actions">
          <button 
            className="back-button-note-page" 
            onClick={() => navigate(-1)}
            disabled={isSaving}
            style={{ marginRight: 'auto' }}
          >
          Back
          </button>
          <button 
            className="edit-button"
            onClick={() => setIsEditing(!isEditing)}
            disabled={isSaving}
            style={{ marginLeft: 'auto' }}
          >
            {isEditing ? 'Cancel' : 'Edit'}
          </button>
          {isEditing && (
            <button 
              onClick={handleSave}
              disabled={isSaving}
            >
              {isSaving ? 'Saving...' : 'Save'}
            </button>
          )}
        </div>
      </main>
    </div>
  );
}