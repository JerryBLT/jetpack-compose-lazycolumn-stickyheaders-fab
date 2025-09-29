# jetpack-compose-lazycolumn-stickyheaders-fab
A Jetpack Compose project featuring a contact list with alphabetical sticky headers and a floating action button (FAB) that appears to scroll to the top after scrolling.

## Features
- **LazyColumn** with **stickyHeader** for alphabetical groups
- **50+ sample contacts** generated in code
- **Floating Action Button** (FAB) to scroll back to top
- FAB only appears when scrolled past item 10
- Uses `animateScrollToItem()` with coroutines for smooth animation

## Getting Started
### Prerequisites
- Android Studio (latest version recommended)

### How to Run
1. Clone the repository:
2. Open the project in Android Studio.

## Project Structure
- `MainActivity.kt`: Contains main Compose UI and state code.
- `ui.theme`: Contains app theme and styling.

## Usage
- Build and run the app to see an alphabetically grouped contact list with sticky headers.
- The list contains over 50 sample contacts generated in code and groups them by first letter.
- Use the scrollable LazyColumn for efficient list rendering and smooth scrolling.
- As you scroll past the 10th item, a "Scroll to Top" floating action button appears.
- Tap the button to smoothly scroll back to the top using animateScrollToItem with coroutines.
- This implementation demonstrates Jetpack Compose best practices for large lists with sticky headers and dynamic UI elements

## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Author
Jerry Teixeria  
BU Email: jerrybt@bu.edu
