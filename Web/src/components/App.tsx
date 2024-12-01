import { MantineProvider } from '@mantine/core';
import RegistrationComponent from "./RegistrationComponent.tsx";

export default function App() {
    return <MantineProvider><RegistrationComponent></RegistrationComponent></MantineProvider>;
}
