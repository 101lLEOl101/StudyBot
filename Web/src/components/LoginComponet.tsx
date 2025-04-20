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
import {useForm} from '@mantine/form';
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import store from "../redux/store.ts";
import {useLoginMutation} from "../redux/slice/api/authApiSlice.ts";
import {setCredentials} from "../redux/slice/authSlice.ts";
import {useEffect} from "react";


type RootState = ReturnType<typeof store.getState>

export function LoginComponet(props: PaperProps) {
    const {user} = useSelector((state: RootState) => state.user);
    const form = useForm({
        initialValues: {
            nickName: '',
            password: '',
        },
    });
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [login, { isLoading, isError }] = useLoginMutation();


    const handleLogin = async () => {
        const res = await login(form).unwrap();
        dispatch(setCredentials(res));
        navigate("/tests");
    };
    useEffect(() => {
        if (user) {
            navigate("/tests");
        }
    }, [user]);
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
                        value={form.values.nickName}
                        onChange={(event) => form.setFieldValue('nickName', event.currentTarget.value)}
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
                {isError && (
                    <Text color="red" size="sm" mt="sm">
                        Неправильный логин или пароль
                    </Text>
                )}
                {isLoading && (
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