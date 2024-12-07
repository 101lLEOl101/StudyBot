import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './styles/index.css'
import App from './components/App.tsx'
import '@mantine/core/styles.css'
import { createTheme, MantineProvider } from "@mantine/core";

const theme = createTheme({
});


createRoot(document.getElementById('root')!).render(
    <MantineProvider theme={theme} defaultColorScheme={'dark'} >
        <StrictMode>
            <App />
        </StrictMode>
    </MantineProvider>
)
