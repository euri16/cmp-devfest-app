# DevFest App

This is a simple multiplatform app built using **Jetpack Compose Multiplatform**. The app lists the speakers of an event and allows users to view details about specific talks.

## Tech Stack

- **Compose Multiplatform**: For building declarative UI across platforms (Android, iOS, Desktop).
- **AndroidX ViewModel**: To manage UI-related data in a lifecycle-conscious way.
- **Compose Navigation**: For navigating between the home screen and the talk detail screen.
- **Firebase Firestore**: Used as the backend database to store and retrieve speaker and talk data.

## Screens

1. **Home Screen**
    - Displays a list of event speakers.
    - Each speaker entry is clickable and navigates to the Talk Detail screen.

2. **Talk Detail Screen**
    - Shows detailed information about a selected talk, including speaker details, the topic of the talk, and the schedule.

## Firebase Firestore Structure

The data in Firebase Firestore should be structured as follows:

### Collection: `talks`

Each document in the `talks` collection represents a talk and contains the following fields:

- `title`: **String** – The title of the talk.
- `description`: **String** – A description of the talk.
- `speaker`: **Map** – A map containing information about the speaker, with the following fields:
   - `name`: **String** – The name of the speaker.
   - `headline`: **String** – A brief headline or title for the speaker (e.g., job title or role).
   - `bio`: **String** – A biography of the speaker.
   - `imageUrl`: **String** – A URL linking to the speaker's image.

### Example Document:

```json
{
  "title": "The Future of AI",
  "description": "An in-depth discussion on the advancements in AI.",
  "speaker": {
    "name": "John Doe",
    "headline": "AI Researcher at OpenAI",
    "bio": "John Doe has been researching AI for over 10 years...",
    "imageUrl": "https://example.com/johndoe.jpg"
  }
}
```

## Installation and Setup

### Prerequisites
- Android Studio (with Kotlin support)
- Firebase Firestore account

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/yourrepo.git
   cd yourrepo
   Open in Android Studio
   
2. **Import the project in Android Studio.**

3. **Firebase Setup**
   - Set up Firebase Firestore for your project.
   - Add your Firebase configuration file (google-services.json) in the appropriate directory.

4. **Run the App**
   - Connect a device or emulator.
   - Click "Run" in Android Studio.

### Contributing

Feel free to fork the project, submit issues, or open pull requests!

### License

This project is licensed under the MIT License - see the LICENSE file for details.