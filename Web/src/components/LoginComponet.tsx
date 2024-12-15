import {
    Box,
    Button,
    Divider,
    Group, Loader,
    Paper,
    PaperProps,
    PasswordInput,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import {useForm, UseFormReturnType} from '@mantine/form';
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {useMutation} from "@tanstack/react-query";
import {axiosConfig} from "../../axios.ts";


export function LoginComponet(props: PaperProps) {
    const form = useForm({
        initialValues: {
            login: '',
            password: '',
        },
    });
    const LoginFun = async (form: UseFormReturnType<{ login: string; password: string }>) => {
        const body = {
            nickname: form.values.login,
            password: form.values.password,
        };
        return (await axiosConfig.post('/api/worker/sign-in', body)).data;
    }
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();
    const [loadingMessage, setLoadingMessage] = useState(false)

    const {mutate} = useMutation(LoginFun, {
        onSuccess: (data) => {
            localStorage.setItem('userId', data.data.id);
            localStorage.setItem('userRole', data.data.workerRole);
            navigate('/active-tests');
        },
        onError: () => {
            setLoadingMessage(false);
            setErrorMessage("Неправильный логин или пароль");
        },
        onSettled: () => {
            setLoadingMessage(false);
        },
    });

    const handleLogin = () => {
        setLoadingMessage(true);
        setErrorMessage("");
        mutate(form);
    };
    return (
        <Paper radius="md" p="xl" withBorder {...props}>
            <Text size="lg" ta={"center"} fw={500}>
                Доступ к Study Bot
            </Text>

            <Divider label={"Логин"} labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(() => {})}>
                <Stack>
                    <TextInput
                        label="Логин"
                        placeholder="Твой Логин"
                        value={form.values.login}
                        onChange={(event) => form.setFieldValue('login', event.currentTarget.value)}
                        radius="md"
                    />

                    <PasswordInput
                        label="Пароль"
                        placeholder="Твой Пароль"
                        value={form.values.password}
                        onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
                        error={form.errors.password && 'Пароль должен иметь не менее 6 символов'}
                        radius="md"
                    />
                </Stack>
                {errorMessage && (
                    <Text color="red" size="sm" mt="sm">
                        {errorMessage}
                    </Text>
                )}
                {loadingMessage && (
                    <Box pt = {10} style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                        <Loader size="lg"/>
                    </Box>
                )}
                <Group justify="center" mt="xl">
                    <Button onClick={handleLogin} type="submit" radius="xl" >
                        Войти
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}